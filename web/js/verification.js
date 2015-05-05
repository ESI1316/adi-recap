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
            club: { required: true, number:true },
            password: { required: true }
            
            
        },
        
        messages: {
            club: {
                required: 'Identifiant obligatoire',
                number: 'Identifiant est un nombre'
            },
            password: {
                required: 'Mot de passe obligatoire'
            }
        }
    });
    
    $('#form-encodage').validate({
       rules: {
           'id-home': {
               required: true,
               number: true,
               range: [0, 100]
           },
           'id-visiteur': {
               required: true,
               number: true,
               range: [0, 100]
           },
           'score-home': {
               required: true,
               number: true,
               range: [0, 100]
           },
           'score-visiteur': {
               required: true,
               number: true,
               range: [0, 100]
           },
           journee: {
               required:true,
               number: true,
               range: [0, 100]
           }
       },
       messages: {
           'id-home': {
               required: 'Requis',
               number: 'Nombre requis',
               range: 'Nombre positif requis'
           },
           'id-visiteur': {
               required: 'Requis',
               number: 'Nombre requis',
               range: 'Nombre positif requis'
           },
           'score-home': {
               required: 'Requis',
               number: 'Nombre requis',
               range: 'Nombre positif requis'
           },
           'score-visiteur': {
               required: 'Requis',
               number: 'Nombre requis',
               range: 'Nombre positif requis'
           },
           journee: {
               required: 'Requis',
               number: 'Nombre requis',
               range: 'Nombre positif requis'
           }
       }
    });
});