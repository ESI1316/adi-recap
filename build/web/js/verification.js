$(function()
{
    
    $('#form').validate({
   
        rules: {
            club: {
                required: function()
                {
                    return $('#equipe').val() !== null && $('#equipe').val() !== "";
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