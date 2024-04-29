# Projeto Rick and Morty Challenge

Este projeto é uma aplicação Android desenvolvida em [**Kotlin**][kotlin] , seguindo as diretrizes do [**MVVM - Model View ViewModel**][mvvm], utilizando o Gradle como sistema de build. O aplicativo é baseado no universo de Rick and Morty, permitindo aos usuários pesquisar e filtrar personagens.

## Características

- Pesquisa de personagens por nome e status (VIVO, MORTO, DESCONHECIDO)
- Filtragem de personagens por status
- Navegação detalhada para cada personagem
- Implementação do Jetpack Compose para a interface do usuário
- Utilização do Paging 3 para paginação dos dados
- Injeção de dependência com [Koin][koin]
- [Coil][coil] para renderizar as imagens

## Estrutura do Projeto

O projeto é dividido em três módulos principais:

1. `app`: Este é o módulo da aplicação Android. Contém a interface do usuário e a lógica de apresentação.
2. `domain`: Este módulo contém a lógica de negócios e os casos de uso. Ele é independente de qualquer framework e pode ser reutilizado em diferentes projetos Android.
3. `data`: Este módulo é responsável por buscar dados de diferentes fontes, nesse momento só usando a API e fornecê-los ao módulo do domínio.

## Como executar

1. Clone este repositório
2. Abra o projeto no Android Studio
3. Execute o aplicativo em um emulador ou dispositivo físico


[mvvm]:<https://medium.com/android-dev-br/arquiteturas-em-android-mvvm-kotlin-android-architecture-components-databinding-lifecycle-d5e7a9023cf3>
[kotlin]:<https://kotlinlang.org/docs/reference/>
[koin]:<https://insert-koin.io/docs/reference/introduction>
[coil]:<https://coil-kt.github.io/coil/getting_started/>
