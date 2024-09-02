jQuery.validator.addMethod("temMaiuscula", function (value, element) {
    return /[A-Z]/.test(value);
}, "O campo deve conter pelo menos uma letra maiúscula.");

jQuery.validator.addMethod("temMinuscula", function (value, element) {
    return /[a-z]/.test(value);
}, "O campo deve conter pelo menos uma letra minúscula.");

jQuery.validator.addMethod("temNumero", function (value, element) {
    return /[0-9]/.test(value);
}, "O campo deve conter pelo menos um número.");

jQuery.validator.addMethod("comparaSenha", function (value, element, params) {
    return value === $(params).val();
}, "As senhas não são iguais.");

jQuery.validator.addMethod("minTime", function(value, element) {
    // Obtém a data e hora atuais
    var now = new Date();
            
    // Ajusta para meia hora antes
    now.setMinutes(now.getMinutes() - 30);
            
    // Obtém a data e hora do input
    var inputDate = new Date(value);
            
    // Verifica se a data do input é maior ou igual a meia hora antes da data atual
    return this.optional(element) || inputDate >= now;
}, "A data e hora devem ser pelo menos meia hora maior ou igual à atual.");

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
    $(".tab-link").click(function (e) {
        e.preventDefault();
        var tab_id = $(this).attr("data-tab");
        var currentTab = $(".tab-link.active").attr("data-tab");

        // Permite ir para aba anterior sem validação
        if ($(this).index() < $(".tab-link.active").index()) {
            $(".tab-link").removeClass("active");
            $(".tab-content").removeClass("active");

            $(this).addClass("active");
            $("#" + tab_id).addClass("active");
        } else {
            // Valida antes de prosseguir para a próxima aba
            if ($("#formularioCarrinho").valid()) {
                $(".tab-link").removeClass("active");
                $(".tab-content").removeClass("active");

                $(this).addClass("active");
                $("#" + tab_id).addClass("active");
            }
        }
    });
    
    // MASCARAS
    $('#telefone').mask('(00)00000-0000');
    $('#cep').mask('00000-000');
    $('#cpf').mask('000.000.000-00');
    $('#validade').mask('00/00');
    
    $("#formularioCarrinho").validate({
        rules: {
            nome: {
                required: true,
                minlength: 4
            },
            email: {
                required: true,
                email: true
            },
            telefone: { required: true },
            endereco: {
                required: true,
                minlength: 3
            },
            cep: {required: true},
            numerocasa: {required: true},
            bairro: {required: true},
            cidade: {required: true},
            uf: {required: true, minlength: 2, maxlength: 2},
            nomecartao: {required: true},
            numerocartao: {required: true},
            cpftitular: {required: true},
            validade: {required: true},
            cvv: {required: true}
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
    
    // TELA DE CADASTRAR USARIO 
    $("#formProduto").validate({
        rules: {
            nome: {
                required: true,
                minlength: 4
            }
        }
    });
    
    // TELA DE AGENDAMENTO
    $("#formAgendamento").validate({
        rules: {
            pet: {
                required: true,
                minlength: 3
            },
            servico: { required: true },
            especie: { required: true },
            data: { 
                required: true,
                minTime: true 
            }
        }
    });
});