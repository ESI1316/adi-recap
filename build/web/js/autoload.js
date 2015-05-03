$(function() {
    
    var $club = $('#select-club');
    
    function updateEquipe()
    {
        $.ajax({
          url: '\UpdateServlet?cible=equipe',
          type: 'get',
          data: {club:$club.val()},
          success: function(equipes)
          {
              $('#select-equipe').html(equipes);
          }
        });
    }
   
   function updateClub()
   {
        $.ajax({
           url: '\UpdateServlet?cible=club',
           type: 'get',
           success: function(clubs)
           {
              $('#select-club').html(clubs);
           }

         });
    }  
   
    
    $('#select-club').on('change', updateEquipe);
    

    updateClub();
    updateEquipe();
  
    
    
   
    
   
});