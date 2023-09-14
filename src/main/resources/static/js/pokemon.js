let pokemonsCadena = [];
$(document).ready(function(){
    let progressBars = $(".stat .progress-bar");
    addProgressRating(progressBars);
})

function getCadenaEvolucion(url) {
    $.ajax({
        async: true,
        type: "GET",
        url: url,
        success: async function(data){
            if (pokemonsCadena.length > 0) {
                pokemonsCadena = [];
            }
            await obtenerEvoluciones(data.chain)
            if (pokemonsCadena.length > 0) {
                if (pokemonsCadena.length == 1) {
                    $(".cadena-evolucion__container .title").after("<span>Este pokemon no evoluciona</span>")
                }
                for (let i = 0; i < pokemonsCadena.length; i++) {
                    $("#cadena-evolucion").append(addEvolutionCard(pokemonsCadena[i]));
                    if (i != pokemonsCadena.length - 1) {
                        $("#cadena-evolucion").append("<i class='bi arrow'></i>");
                    }
                }
            }
        }
    });
}

async function obtenerEvoluciones(cadena) {
    let promise = "";

    // Hacer una solicitud para obtener la información de species
    const pokemonSpecies = await fetch(cadena.species.url).then((response) =>
        response.json()
    );

    for (const variety of pokemonSpecies.varieties) {
        if (variety.is_default) {
            promise = fetch(variety.pokemon.url).then((response) => response.json());
        }
    }

    // Esperar a que todas las promesas se completen
    pokemonsCadena.push(await Promise.resolve(promise));

    if (cadena.evolves_to.length > 0) {
        for (const evolucion of cadena.evolves_to) {
            await obtenerEvoluciones(evolucion);
        }
    }
    return pokemonsCadena;
}

function addProgressRating(progressBars) {
    for (const progressBar of progressBars) {
        let rating = $(progressBar).attr("value");
        if (rating >= 0 && rating < 30) {
            $(progressBar).addClass("bg-danger")
        } 
        else if(rating >= 30 && rating < 50){
            $(progressBar).addClass("bg-warning")
        }
        else{
           $(progressBar).addClass("bg-success") 
        }
    }
}

function addEvolutionCard(pokemon) {
    let card = `<div class="evolution-card">
        <figure class="m-0">
            <img src="${pokemon.sprites.other['official-artwork'].front_default}" alt="${pokemon.name}" class="d-block img-fluid">
        </figure>
        <div class="pokemon__info">
            <span class="pokemon__name">${capitalize(pokemon.name)}</span>
            <span class="pokemon__id">${'Nº ' + pokemon.id}</span>
        </div>
        <div class="types">`;

        pokemon.types.forEach(type => {
            card += `<span class="pokemon__type ${eliminaTildes(typesInSpanish[type.type.name].toLowerCase())}">${typesInSpanish[type.type.name]}</span>`            
        });
    card += `</div>
    </div>`;
    return card;
}