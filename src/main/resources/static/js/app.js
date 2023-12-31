const typesInSpanish = {
    normal: "Normal",
    fighting: "Lucha",
    flying: "Volador",
    steel: "Acero",
    water: "Agua",
    bug: "Bicho",
    dragon: "Dragón",
    electric: "Eléctrico",
    ghost: "Fantasma",
    fire: "Fuego",
    fairy: "Hada",
    ice: "Hielo",
    grass: "Planta",
    psychic: "Psíquico",
    rock: "Roca",
    dark: "Siniestro",
    ground: "Tierra",
    poison: "Veneno"    
};

$(document).ready(function(){
  $(".my_random_team__container .btn-generate-randomTeam").click(function(){
    generateRandomTeam(urlRandomTeam, false)
  }) 
  $(".my_random_against_team .btn-generate-randomTeam").click(function(){
    generateRandomTeam(urlRandomTeam, true)
  }) 

  $(document).scroll(function(){
    if (scrollY>100) {
      $("#btn-scrollToTop").fadeIn();
    } 
    else{
      $("#btn-scrollToTop").fadeOut();
    }
  })

  $("#btn-scrollToTop").click(function(){
    scrollTo(0,0);
  })
})

function generateRandomTeam(url, isAgainst) {
  let container = $(".my_team.pokemon__box");
  let boton = $(this);
  $.ajax({
      async: true,
      type: "POST",
      url: "/my-team/generate",
      contentType: "application/json",
      data: isAgainst ? JSON.stringify(tipos) : undefined,
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
          data.forEach(pokemon => {
              let typesHTML = '';
              pokemon.types.forEach(type => {
                typesHTML += `<span class='${eliminaTildes(type.type.name)} pokemon__type'>${capitalize(type.type.name)}</span>`;
              });            
              $(".my_team.pokemon__box").append(`<div class='card position-relative'>
              <a class='position-absolute w-100 h-100 z-2' href="/pokemon/${pokemon.name}"></a>
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
}

function loadPokemonList(url, pokedexContainer){
    $.ajax({
        async: true,
        type: "GET",
        url: url,
        beforeSend: function() {
          pokedexContainer.prepend(
            "<div class='spinner-container position-absolute top-0 start-0 w-100 h-100 d-flex align-items-center justify-content-center'>"+
            "<div class='spinner spinner-border text-light' role='status'>"+
            "<span class='visually-hidden'>Loading...</span>"+
            "</div>"+
            "</div>")
          },
          success: function (pokemons) {
            pokedexContainer.html("");
            const promises = [];
            pokemons.results.forEach(pk =>{
              const promise = fetchPokemonDetails(pk.url);
              promises.push(promise);
            })
            Promise.all(promises)
            .then(pokemonList => {
              pokemonList.forEach(pokemon => {
                let typesHTML = '';
                pokemon.types.forEach(type => {
                    typesHTML += `<span class='${eliminaTildes(type.type.name)} pokemon__type'>${capitalize(type.type.name)}</span>`;
                }); 
                pokedexContainer.append(`<div class="pokemon-card">
                            <figure class="m-0">
                                <img loading="lazy" src="${pokemon.sprites.other['official-artwork'].front_default}" alt="${capitalize(pokemon.name)}" class="d-block img-fluid">
                            </figure>
                            <div class="pokemon__info p-1">
                                <p class="pokedex__number fw-bold m-0">${'Nº' + pokemon.id}</p>
                                <p class="pokemon__name fw-bold m-0 mt-2">${capitalize(pokemon.name)}</p>
                                <div class="types d-flex gap-1 mt-1">
                                ${typesHTML}
                                </div>
                            </div>
                        </div>`);
              });
              // $(".spinner-container").remove();
              $(".container").removeClass("vh-100");
              if (totalPaginas > 1) {
                $(".pagination").removeClass("d-none");
              }
          })
          .catch(error => {
              console.error(error);
          })
        }      
    })
}

function loadPokemonPaginateList(page, cleanContainer) {
    $.ajax({
      async: true,
      type: "GET",
      url: url,
      data: {page: page},
      beforeSend: function() {
        pokedexContainer.prepend(
          "<div class='spinner-container position-absolute top-0 start-0 w-100 h-100 d-flex align-items-center justify-content-center'>"+
          "<div class='spinner spinner-border text-light' role='status'>"+
          "<span class='visually-hidden'>Loading...</span>"+
          "</div>"+
          "</div>")
        },
        success: function (pokemons) {
          if (cleanContainer) {
            pokedexContainer.html("");
          }
          const promises = [];
          pokemons.forEach(pk =>{
            const promise = fetchPokemonDetails(pk.url);
            promises.push(promise);
          })
          Promise.all(promises)
          .then(pokemonList => {
            $(".spinner-container").remove();
            pokemonList.forEach(pokemon => {
              let typesHTML = '';
              pokemon.types.forEach(type => {
                  typesHTML += `<span class='${eliminaTildes(typesInSpanish[type.type.name].toLowerCase())} pokemon__type'>${typesInSpanish[type.type.name]}</span>`;
              }); 
              pokedexContainer.append(`<div class="pokemon-card">
                          <figure class="m-0">
                              <img loading="lazy" src="${pokemon.sprites.other['official-artwork'].front_default}" alt="${capitalize(pokemon.name)}" class="d-block img-fluid">
                          </figure>
                          <div class="pokemon__info p-1">
                              <p class="pokedex__number fw-bold m-0">${'Nº' + pokemon.id}</p>
                              <p class="pokemon__name fw-bold m-0 mt-2">${capitalize(pokemon.name)}</p>
                              <div class="types d-flex gap-1 mt-1">
                              ${typesHTML}
                              </div>
                          </div>
                      </div>`);
            });
            // $(".spinner-container").remove();
            $(".container").removeClass("vh-100");
            if (typeof totalPaginas !== "undefined" && totalPaginas > 1) {
              $(".pagination").removeClass("d-none");
            }
        })
        .catch(error => {
            console.error(error);
        })
      }      
    })
}


function fetchPokemonDetails(url) {
  return new Promise((resolve, reject) => {
      $.ajax({
          type: "GET",
          url: url,
          success: function(pokemon) {
              resolve(pokemon);
          },
          error: function(error) {
              reject(error);
          }
      });
  });
}

function getAllAbilities() {
  $.ajax({
    async: true,
    type: "GET",
    url: "/api/pokedex/abilities",
    success: function(abilitiesData){
      // console.log(abilitiesData);
      const promises = [];
      abilitiesData.forEach(abilityData => {
        const promise = fetchPokemonDetails(abilityData.url);
        promises.push(promise);
      });
      Promise.all(promises)
      .then(abilities => {
        abilities.forEach(ability => {
          if (ability.names.some(obj => obj.language.name == 'es')) {
            $("#habilidades").append(`<option value='${ability.names.find(obj => obj.language.name === 'en').name}'>${ability.names.find(obj => obj.language.name === 'es').name}</option>`)
          } else{
            $("#habilidades").append(`<option value='${ability.names.find(obj => obj.language.name === 'en').name}'>${ability.names.find(obj => obj.language.name === 'en').name}</option>`)
          }
        });
      })
    }
  })
}

function getPokemonFilter(filter) {
  $.ajax({
    type: "POST",
    url: url,
    data: filter,
    beforeSend: function() {
      pokedexContainer.prepend(
        "<div class='spinner-container position-fixed top-0 start-0 w-100 h-100 d-flex align-items-center justify-content-center'>"+
        "<div class='spinner spinner-border text-light' role='status'>"+
        "<span class='visually-hidden'>Loading...</span>"+
        "</div>"+
        "</div>")
    },
    success: function (response) {
        if (response.pokemons.length > 0) {
          const promises = [];
          $("#empty-message").html("");
          response.pokemons.forEach(pk =>{
            const promise = fetchPokemonDetails(pk.url);
            promises.push(promise);
          })
          Promise.all(promises)
          .then(pokemonList => {
            pokedexContainer.html("");
            if (response.isFinish) {
              $("#btn-load-more").addClass("d-none");
            } else{
              $("#btn-load-more").removeClass("d-none");
            }
            pokemonList.forEach(pokemon => {
              let typesHTML = '';
              pokemon.types.forEach(type => {
                  typesHTML += `<span class='${eliminaTildes(typesInSpanish[type.type.name].toLowerCase())} pokemon__type'>${typesInSpanish[type.type.name]}</span>`;
              }); 
              pokedexContainer.append(`<div class="pokemon-card">
                          <a href="/pokemon/${pokemon.name}" class='position-absolute w-100 h-100'></a>
                          <figure class="m-0">
                              <img loading="lazy" src="${pokemon.sprites.other['official-artwork'].front_default}" alt="${capitalize(pokemon.name)}" class="d-block img-fluid">
                          </figure>
                          <div class="pokemon__info p-1">
                              <p class="pokedex__number fw-bold m-0">${'Nº' + pokemon.id}</p>
                              <p class="pokemon__name fw-bold m-0 mt-2">${capitalize(pokemon.name)}</p>
                              <div class="types d-flex gap-1 mt-1">
                              ${typesHTML}
                              </div>
                          </div>
                      </div>`);
            });
            $(".container").removeClass("vh-100");
        })
        .catch(error => {
            console.error(error);
            pokedexContainer.html("");
            $("#empty-message").html(`<div class="alert alert-danger" role="alert">
                                      <i class='bi bi-exclamation-triangle-fill'></i>
                                      Se ha producido un error inesperado
                                    </div>`
            );
        })
        } else {
          pokedexContainer.html("");
          $("#empty-message").html(`<div class="alert alert-warning" role="alert">
                                    <i class='bi bi-exclamation-triangle-fill'></i>
                                    No se ha encontrado ningún Pokemon
                                  </div>`
          );
        }
    },
    error: function (){
      pokedexContainer.html("");
      $("#empty-message").html(`<div class="alert alert-danger" role="alert">
                                <i class='bi bi-exclamation-triangle-fill'></i>
                                Se ha producido un error inesperado
                              </div>`
      );
    }
  })
  $(".accordion-button").addClass("collapsed");
  $(".accordion-collapse").removeClass("show");
}

function loadMorePokemon(page) {
  $.ajax({
    type: "GET",
    url: "/api/pokedex/loadMore",
    data: {page: page},
    beforeSend: function() {
      pokedexContainer.prepend(
        "<div class='spinner-container position-fixed top-0 start-0 w-100 h-100 d-flex align-items-center justify-content-center'>"+
        "<div class='spinner spinner-border text-light' role='status'>"+
        "<span class='visually-hidden'>Loading...</span>"+
        "</div>"+
        "</div>")
    },
    success: function (response) {
        const promises = [];
        response.pokemons.forEach(pk =>{
          const promise = fetchPokemonDetails(pk.url);
          promises.push(promise);
        })
        Promise.all(promises)
        .then(pokemonList => {
          $(".spinner-container").remove();
          if (response.isFinish) {
            $("#btn-load-more").addClass("d-none");
          } else{
            $("#btn-load-more").removeClass("d-none");
          }
          pokemonList.forEach(pokemon => {
            let typesHTML = '';
            pokemon.types.forEach(type => {
                typesHTML += `<span class='${eliminaTildes(typesInSpanish[type.type.name].toLowerCase())} pokemon__type'>${typesInSpanish[type.type.name]}</span>`;
            }); 
            pokedexContainer.append(`<div class="pokemon-card">
                        <a href="/pokemon/${pokemon.name}" class='position-absolute w-100 h-100'></a>
                        <figure class="m-0">
                            <img loading="lazy" src="${pokemon.sprites.other['official-artwork'].front_default}" alt="${capitalize(pokemon.name)}" class="d-block img-fluid">
                        </figure>
                        <div class="pokemon__info p-1">
                            <p class="pokedex__number fw-bold m-0">${'Nº' + pokemon.id}</p>
                            <p class="pokemon__name fw-bold m-0 mt-2">${capitalize(pokemon.name)}</p>
                            <div class="types d-flex gap-1 mt-1">
                            ${typesHTML}
                            </div>
                        </div>
                    </div>`);
          });
          $(".container").removeClass("vh-100");
      })
      .catch(error => {
          console.error(error);
      })
    } 
  })
}

function eliminaTildes(txt) {
    return txt.normalize("NFD").replace(/[\u0300-\u036f]/g, '');
}
function capitalize(txt) {
    return txt.charAt(0).toUpperCase() + txt.slice(1).toLowerCase();
}