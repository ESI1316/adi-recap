$(function()
{
    
    $('#form').validate({
   
        rules: {
            club: {
                required: function()
                {
                    return $('#select-equipe').val() !== null && $('#select-equipe').val() !== "";
                }
            }
        },
        
        messages: {
            club: {
                required: 'Le club est obligatoire'
            }
        }

    });
});