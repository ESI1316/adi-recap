$(function()
{
    
    $('#form-liste').validate({
   
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
    
    $('#form-ident').validate({
        rules: {
            club: { required: true },
            password: { required: true }
            
        },
        
        messages: {
            club: {
                required: 'Identifiant obligatoire'
            },
            password: {
                required: 'Mot de passe obligatoire'
            }
        }
    });
});