$(document).ready(function(){
    pokedexContainer.html("");
    loadPokemonPaginateList(page, true);
    $(pageLinks[1]).addClass("active");
    $(pageLinks[0]).addClass("disabled");

    $(".page-link").click(function(){
      if (this.classList.contains("page-item-next")) {
        page++;
      } else if(this.classList.contains("page-item-previous")){
        page--;
      } else{
        page = parseInt($(this).html());
      }
      $(".page-link.active").removeClass("active");
      if (page == 1) {
        $(pageLinks[0]).addClass("disabled");
        $(pageLinks[pageLinks.length - 1]).removeClass("disabled");
      } else if(page == totalPaginas){
        $(pageLinks[pageLinks.length - 1]).addClass("disabled");
        $(pageLinks[0]).removeClass("disabled");
      } else{
        $(pageLinks[pageLinks.length - 1]).removeClass("disabled");
        $(pageLinks[0]).removeClass("disabled");
      }
      $(pageLinks[page]).addClass("active");
      loadPokemonPaginateList(page, true);
    })
  });