jQuery.validator.addMethod("temMaiuscula", function (value, element) {
    return /[A-Z]/.test(value);
}, "O campo deve conter pelo menos uma letra maiúscula.");

jQuery.validator.addMethod("temMinuscula", function (value, element) {
    return /[a-z]/.test(value);
}, "O campo deve conter pelo menos uma letra minúscula.");

jQuery.validator.addMethod("temNumero", function (value, element) {
    return /[0-9]/.test(value);
}, "O campo deve conter pelo menos um número.");

jQuery.validator.addMethod("comparaSenha", function(value, element, params) {
    return value === $(params).val();
}, "As senhas não são iguais.");

$(document).ready(function () {
    // TELA DE CADASTRO
    $("#formularioCadastro").validate({
        rules: {
            nome: {
                required: true,
                minlength: 4
            },
            endereco: {
                required: true,
                minlength: 4
            },
            email: {
                required: true,
                email: true
            },
            senha: {
                required: true,
                minlength: 8,
                temMaiuscula: true,
                temMinuscula: true,
                temNumero: true
            },
            confirmaSenha: {
                required: true,
                comparaSenha: "#senha"
            }
        }
    });
    
    // TELA DE CADASTRAR USARIO 
    $("#formCriaUsuario").validate({
        rules: {
            nome: {
                required: true,
                minlength: 4
            },
            endereco: {
                required: true,
                minlength: 4
            },
            email: {
                required: true,
                email: true
            },
            adm: {
                required: true
            },
            senha: {
                required: true,
                minlength: 8,
                temMaiuscula: true,
                temMinuscula: true,
                temNumero: true
            },
            confirmaSenha: {
                required: true,
                comparaSenha: "#senha"
            }
        }
    });
    
    // FORMULARIO MARCAS
    $("#formularioMarca").validate({
        rules: {
            marca: {
                required: true,
                minlength: 4
            }
        }
    });
    
    // FORMULARIO CATEGORIA
    $("#formularioCategoria").validate({
        rules: {
            categoria: {
                required: true,
                minlength: 4
            }
        }
    });
    
    // FORMULARIO ESPECIES
    $("#formularioEspecie").validate({
        rules: {
            especie: {
                required: true,
                minlength: 4
            }
        }
    });
    
    // FORMULARIO CARRINHO DE COMPRA
    $("#formularioCarrinho").validate({
        rules: {
            nome: {
                required: true,
                minlength: 4
            },
            email: {
                required: true,
                email: true
            }
        }
    });
    
    // TELA DE CADASTRAR USARIO 
    $("#formConta").validate({
        rules: {
            nome: {
                required: true,
                minlength: 4
            },
            endereco: {
                required: true,
                minlength: 4
            },
            email: {
                required: true,
                email: true
            },
            adm: {
                required: true
            },
            senha: {
                required: true,
                minlength: 8,
                temMaiuscula: true,
                temMinuscula: true,
                temNumero: true
            },
            confirmaSenha: {
                required: true,
                comparaSenha: "#senha"
            }
        }
    });
});