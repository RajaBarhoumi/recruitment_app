package Rappel;

public class Profil {
    String first_name;
    String last_name;
    String nickname;

    public Profil() {
    }

    public Profil(String first_name, String last_name, String nickname) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Profil{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public static Profil getProfilByNickname(String nickname){
        Profil p = null;
        for (int i = 0; i< Data.profils.size(); i++) {
            if (Data.profils.get(i).getNickname().equals(nickname)) {
                return Data.profils.get(i);
            }
        }
        return p;
    }
}
