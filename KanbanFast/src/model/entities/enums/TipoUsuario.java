package model.entities.enums;

/**
 *
 * @author Alexandre dos Santos Cunha <alexandre.sc115@gmail.com>
 * @date 16/11/2023
 * @brief Class TipoUsuario
 */
public enum TipoUsuario {

    ADMINISTRADOR, // Representa um tipo de usuário com permissões administrativas
    LIDER, // Representa um tipo de usuário que é líder
    FUNCIONARIO; // Representa um tipo de usuário funcionário comum

    // Não há necessidade de construtores, pois as constantes são valores fixos e pré-definidos
}
