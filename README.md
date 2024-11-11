Este aplicativo necessita das permissões de acesso aos arquivos de áudio.

Este aplicativo navega no database do android e armazena todos os arquivos de áudio encontrados.
A música em execução tem um leve destaque.
Fornece um player básico com play/pause, anterior, próximo, exibição da metragem em tempo real, exibição da metragem total e uma barra de execução com interação com usuário.

1. Introdução 
Descrição Geral
O Javafy é um aplicativo de música desenvolvido para dispositivos Android. Ele permite ao usuário visualizar e reproduzir músicas armazenadas localmente no dispositivo, utilizando uma interface simples e intuitiva. O usuário pode navegar pela biblioteca de músicas, selecionar uma faixa para tocar, pausar ou avançar para a próxima faixa, além de acompanhar o progresso da reprodução através de uma barra de progresso.
Motivação
O Javafy foi desenvolvido para explorar a criação de um player de música com funcionalidades essenciais, como controle de reprodução e gerenciamento de lista de músicas. O projeto foi uma oportunidade para aplicar conhecimentos de programação orientada a objetos, padrões de projeto e práticas de boas práticas de desenvolvimento, especialmente no contexto do desenvolvimento Android.



2. Padrões de Projeto

Template Method (Login)
O Template Method foi aplicado no fluxo de login para definir uma sequência padrão de autenticação que pode ser adaptada de acordo com o tipo de usuário (por exemplo, usuário comum ou administrador). Esse padrão ajuda a manter a estrutura de autenticação consistente, com flexibilidade para ajustar o comportamento específico em subclasses.
public abstract class UserLoginTemplate {
    public final void loginProcess() {
        validateCredentials();
        authenticateUser();
        redirectToHome();
    }
    protected abstract void validateCredentials();
    protected abstract void authenticateUser();
    
    private void redirectToHome() {
        // Redireciona para a tela principal
    }
}
Singleton (MediaPlayer)
O padrão Singleton foi utilizado para o gerenciamento da instância de MediaPlayer, garantindo que apenas uma instância ativa controle a reprodução de música. Isso evita a criação de múltiplas instâncias, que poderiam causar problemas de desempenho e consumo de recursos.
public class MediaPlayerSingleton {
    static MediaPlayer instance;

    public static MediaPlayer getInstance() {
        if (instance == null) {
            instance = new MediaPlayer();
        }
        return instance;
    }
    public static int currentIndex = -1;
}
Adapter (RecyclerView)
O padrão Adapter é usado implicitamente pelo MusicListAdapter para adaptar a lista de músicas para exibição no RecyclerView. O Adapter conecta os dados (lista de músicas) com a interface gráfica (RecyclerView) e permite manipular cada item individualmente.
public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {
    ArrayList<Music> musicList;
    Context context;

    public MusicListAdapter(ArrayList<Music> musicList, Context context) {
        this.musicList = musicList;
        this.context = context;
    }

    // métodos onCreateViewHolder, onBindViewHolder e getItemCount
}
Observer (Atualização da Interface do Player):
O padrão Observer atualização da interface enquanto a música é reproduzida com o uso de Handler para atualizar o SeekBar e outras informações. A interface gráfica "observa" o progresso da música e atualiza a barra de progresso e o botão de play/pause em tempo real.
new Handler().postDelayed(this, 100);  // Atualiza o SeekBar a cada 100ms para refletir o progresso da música
Strategy (Organização de Playlist)
O Strategy permite que a lista de músicas seja organizada de diferentes formas, como por gênero, artista, ou título. Ao implementar o Strategy, diferentes algoritmos de organização podem ser intercambiáveis sem modificar a estrutura principal da playlist. Isso permite que a aplicação selecione uma organização específica conforme a preferência do usuário, deixando o código da playlist mais flexível e extensível.
import java.util.List;
public class Playlist {
    private List<Music> songs;
    private PlaylistStrategy strategy;

    public Playlist(List<Music> songs) {
        this.songs = songs;
    }

    // Método para definir a estratégia de organização
    public void setStrategy(PlaylistStrategy strategy) {
        this.strategy = strategy;
    }

    // Método para organizar a playlist conforme a estratégia atual
    public void organizePlaylist() {
        if (strategy != null) {
            strategy.organize(songs);
        }
    }

    public List<Music> getSongs() {
        return songs;
    }
}
3. Princípios de Projeto
Responsabilidade Única (SRP)
Cada classe do sistema foi projetada para realizar uma única função. Por exemplo, Music é responsável por armazenar dados das músicas, MediaPlayerSingleton gerencia a instância do reprodutor de mídia, e MusicListAdapter é responsável por configurar a exibição das músicas em uma lista.
public class Music implements Serializable {
    private String path;
    private String title;
    private String duration;

    public Music(String path, String title, String duration) {
        this.path = path;
        this.title = title;
        this.duration = duration;
    }

    // Getters e Setters
}
Aberto/Fechado (OCP)
O código foi escrito para ser extensível, permitindo adicionar novos tipos de login sem modificar o código existente. O Template Method no processo de login exemplifica esse princípio, já que permite implementar diferentes variações de autenticação sem alterar a estrutura principal.
public class AdminLogin extends UserLoginTemplate {
    @Override
    protected void validateCredentials() {
        // Validação específica para administrador
    }
    
    @Override
    protected void authenticateUser() {
        // Autenticação específica para administrador
    }
}
Inversão de Dependência (DIP)
As dependências, como a lista de músicas no adaptador MusicListAdapter, são injetadas de fora, permitindo que a classe funcione independentemente dos dados que está processando. Esse princípio facilita a testabilidade e a manutenção.
public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {
    ArrayList<Music> musicList;
    Context context;

    public MusicListAdapter(ArrayList<Music> musicList, Context context) {
        this.musicList = musicList;
        this.context = context;
    }

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {
    ArrayList<Music> musicList;
    Context context;

    public MusicListAdapter(ArrayList<Music> musicList, Context context) {
        this.musicList = musicList;
        this.context = context;
    }
}
4. Framework
Android MediaPlayer
O MediaPlayer do Android foi escolhido para gerenciar a reprodução de áudio, pois oferece uma API simples para manipular arquivos de áudio locais e oferece suporte a operações como iniciar, pausar e parar a reprodução.
mediaPlayer.setDataSource(actualMusic.getPath());
mediaPlayer.prepare();
mediaPlayer.start();
Vantagens e Desvantagens
Vantagens: Fácil de implementar e permite controle detalhado sobre o áudio. Integrado ao Android, o que facilita a compatibilidade.
Desvantagens: Algumas operações exigem atenção ao ciclo de vida (como liberar a instância quando não é mais necessária). O MediaPlayer não é ideal para streaming de áudio, limitando o aplicativo a arquivos locais.
5. Conclusão
Lições Aprendidas
Trabalhar com os padrões Singleton e Template Method trouxe uma organização clara e facilitou a manutenção do código, além de deixar o sistema mais preparado para crescer no futuro. O Singleton foi fundamental para gerenciar o reprodutor de música, garantindo que sempre houvesse uma única instância controlando a reprodução, o que simplificou bastante o código. Já o Template Method ajudou a definir um fluxo consistente de autenticação, mas com a flexibilidade necessária para atender diferentes tipos de usuários. Com esses padrões, ficou claro como uma arquitetura bem pensada, onde cada classe e método tem um papel específico, faz toda a diferença — facilita o entendimento, a organização e a adaptação do sistema.
Dificuldades Encontradas
Um dos desafios mais marcantes foi sincronizar o estado do MediaPlayer com a interface, especialmente ao atualizar o progresso da música em tempo real. Foi um processo que exigiu bastante atenção, já que pequenos atrasos ou desajustes poderiam impactar a experiência do usuário. Além disso, implementar o Template Method para o login de diferentes tipos de usuários exigiu um bom planejamento para manter a estrutura de autenticação consistente, sem perder a flexibilidade necessária para cada perfil. Essas dificuldades reforçaram a importância de um bom planejamento arquitetural e de escolher padrões de projeto que realmente atendam às necessidades específicas do sistema.

6. Autores 

Matheus Antonio Pereira Mendes 

