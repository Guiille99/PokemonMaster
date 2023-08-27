$(document).ready(function(){
    $(".btn-generate-randomTeam").click(function() {
        let container = $(".my_team.pokemon__box");
        let boton = $(".btn-generate-randomTeam");
        $.ajax({
            async: true,
            type: "GET",
            url: "/my-team/generate",
            beforeSend: function() {
                boton.prop('disabled', true);
                $(".my_team.pokemon__box").prepend(
                    "<div class='spinner-container position-absolute top-0 start-0 w-100 h-100 d-flex align-items-center justify-content-center'>"+
                        "<div class='spinner spinner-border text-light' role='status'>"+
                         "<span class='visually-hidden'>Loading...</span>"+
                        "</div>"+
                    "</div>")
            },
            success: function(data){
                container.html("");
                // console.log(data);
                data.forEach(pokemon => {
                    let typesHTML = '';
                    pokemon.types.forEach(type => {
                      typesHTML += `<span class='${eliminaTildes(type.type.name)} pokemon__type'>${capitalize(type.type.name)}</span>`;
                    });            
                    $(".my_team.pokemon__box").append(`<div class='card'>
                    <div class='pokemon__num-img'>
                    <figure class='m-0'>
                        <img loading='lazy' src='${pokemon.sprites.other['official-artwork'].frontDefault}' alt='${capitalize(pokemon.name)}' class='d-block img-fluid'/>
                    </figure>
                    <span class='pokemon__number'>${pokemon.id}</span>
                    </div>
                    <div class='pokemon__info'>
                    <p class='pokemon__name'>${capitalize(pokemon.name)}</p>
                    <div class='types d-flex gap-1 flex-wrap'>
                        ${typesHTML}
                   </div>
                 </div>
                </div>`)
                });
                boton.prop('disabled', false);
            },
            error: function (error){
                console.log(error)
                boton.prop('disabled', false);
            }
        })
    })
})

function eliminaTildes(txt) {
    return txt.normalize("NFD").replace(/[\u0300-\u036f]/g, '');
}
function capitalize(txt) {
    return txt.charAt(0).toUpperCase() + txt.slice(1).toLowerCase();
}