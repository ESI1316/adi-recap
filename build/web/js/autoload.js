$(function() {
    
    var $club = $('#select-club');
    var $equipe = $('#select-equipe');
    
    function updateEquipe()
    {
        $equipe.empty();
        $equipe.append("<option value=''>Aucune équipe choisie</option>");
        
        $.ajax({
          url: 'UpdateServlet?cible=equipe',
          type: 'get',
          dataType: 'json',
          data: {club:$club.val()},
          success: function(json)
          {
              $.each(json, function(i, equipe) 
              {
                  $equipe.append("<option value='" + equipe.num
                        + "' >" + equipe.club.nom + " (" + equipe.num +")" + "</option>");
              });
          }
        });
    }
    
    $('#select-club').on('change', updateEquipe);

    updateEquipe();
});