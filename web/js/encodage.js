$(function() {
    
 

    
    $('#save').on('click', function() {
    
        if ($('#form-encodage').valid())
        {
            
            var $clubH   = $('#select-club-home option:selected');
            var $clubV   = $('#select-club-visiteur option:selected');  
            var dataString = $('#form-encodage').serialize();
                dataString += '&nomClubV=' + $clubV.text();
                dataString += '&nomClubH=' + $clubH.text();
          
            $.ajax({
               type: 'POST',
               url: 'FrontController',
               data: dataString,
               success: function() {
                   alert('Ok');
               },
               error: function()
               {
                   alert('Echec');
               }

           });
        }
    });
});