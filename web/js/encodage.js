$(function() {
    
 
    var $form = $('#form-encodage');
    
    $('#save').on('click', function() {
    
        if ($form.valid())
        {
            var $clubH   = $('#select-club-home option:selected');
            var $clubV   = $('#select-club-visiteur option:selected');  
            var dataString = $('#form-encodage').serialize();
                dataString += '&nomClubV=' + $clubV.text();
                dataString += '&nomClubH=' + $clubH.text();
          
            $.ajax({
               // Si on change l'url, on la change juste
               // dans encodage.jsp
               url: $form.attr('action'),
               type: 'POST',
               data: dataString,
               dataType: 'JSON',
               success: function(data) {
                   $('#rc').html(data.message);
               },
               error: function(jqXHR, textStatus, errorThrown)
               {
                   $('#rc').html(errorThrown);
               }

           });
        }
    });
});