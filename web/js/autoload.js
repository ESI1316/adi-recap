$(function() {
    
    var $clubs = $('.select-club');
    
    // clubId est le club ayant changé.
    function updateEquipe(clubId)
    {
        // La valeur du club.
        var clubValue = $('#'+clubId).val();
        
        // On cherche l'équipe correspondant au club
        // Voir balise html dans encodage.jsp pour mieux comprendre
        var equipeId = '#select-equipe';
        var tab = clubId.split('-');
        if (tab.length === 3)
            equipeId +=  '-' + tab[2];
        
        // On vide la combobox de l'équipe avant de la remplir
        $(equipeId).empty();
        $(equipeId).append("<option value=''>Aucune équipe choisie</option>");
        
        $.ajax({
          url: 'UpdateServlet?cible=equipe',
          type: 'GET',
          dataType: 'JSON',
          data: {club:clubValue},
          success: function(json)
          {
              $.each(json, function(i, equipe) 
              {
                    $(equipeId).append("<option value='" + equipe.num
                        + "' >" + equipe.club.nom + " (" + equipe.num +")" + "</option>");
              });
          }
        });
    }
    
    // onchange event
    $clubs.on('change', function()
    {
        updateEquipe($(this).attr('id'));
    });

   
    // Initialisation au chargement de la page
    $.each($clubs, function(key, club) {
        updateEquipe(club.id); 
    });
   
    
});