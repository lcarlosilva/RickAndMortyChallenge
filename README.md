# Projeto Rick and Morty Challenge

Este projeto é uma aplicação Android desenvolvida em [**Kotlin**][kotlin] , seguindo as diretrizes do [**MVVM - Model View ViewModel**][mvvm], utilizando o Gradle como sistema de build. O aplicativo é baseado no universo de Rick and Morty, permitindo aos usuários pesquisar e filtrar personagens.

### Funcionamento

No vídeo abaixo você pode conferir o resultado final desse APP

https://github.com/lcarlosilva/RickAndMortyChallenge/assets/6023567/917ddfc1-40ab-4a79-949c-0e27c76f35a7

## Características

- Pesquisa de personagens por nome e status (VIVO, MORTO, DESCONHECIDO)
- Filtragem de personagens por status
- Navegação detalhada para cada personagem
- Implementação do Jetpack Compose para a interface do usuário
- Utilização do Paging 3 para paginação dos dados
- Injeção de dependência com [Koin][koin]
- [Coil][coil] para renderizar as imagens

## Estrutura do Projeto

A arquitetura em camadas é um padrão de design de software que separa o código em camadas lógicas para melhor organização e separação de responsabilidades. A arquitetura em camadas é implementada através da modularização, onde cada módulo representa uma camada específica.

|  |
| ---      |
| ![clean-arch](https://github.com/lcarlosilva/RickAndMortyChallenge/assets/6023567/286d8fef-8304-430c-8b7a-ef0a4566f746) |

O projeto é dividido em três módulos principais:

1. `:data` - Este módulo é responsável por lidar com todas as operações de dados, como buscar dados de uma API, ler/escrever em um banco de dados local, etc. Ele não sabe nada sobre a interface do usuário e apenas fornece os dados necessários para outras camadas.  
2. `:domain` - Este módulo contém a lógica de negócios do aplicativo. Ele define as regras de negócios que o aplicativo deve seguir. Ele é independente de qualquer outra camada e não deve saber como os dados são obtidos ou como são apresentados ao usuário.  
3. `:commons` - Este módulo pode conter código que é comum a vários outros módulos, como utilitários, extensões de função, etc.  
4. `:app` - Este é o módulo do aplicativo que contém a interface do usuário e a lógica de apresentação. Ele depende dos módulos :data e :domain para obter os dados necessários e aplicar as regras de negócios, respectivamente.

   Essa separação em camadas permite que cada parte do código tenha responsabilidades claras e bem definidas, tornando o código mais fácil de entender, testar e manter. Além disso, a modularização permite que partes do código sejam reutilizadas em diferentes partes do projeto ou até mesmo em projetos diferentes.

## Como executar

1. Clone este repositório
2. Abra o projeto no Android Studio
3. Execute o aplicativo em um emulador ou dispositivo físico


[mvvm]:<https://medium.com/android-dev-br/arquiteturas-em-android-mvvm-kotlin-android-architecture-components-databinding-lifecycle-d5e7a9023cf3>
[kotlin]:<https://kotlinlang.org/docs/reference/>
[koin]:<https://insert-koin.io/docs/reference/introduction>
[coil]:<https://coil-kt.github.io/coil/getting_started/>
