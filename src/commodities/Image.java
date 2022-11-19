package commodities;

public class Image {
    private String[] images = {
            "https://p1.meituan.net/biztone/66cb92af913477b3eadf8cad5b49e2e7197848.png@428w_240h_1e_1c",
            "https://p1.meituan.net/msmerchant/bf85dbe137a9c06e104cfe62748f31fd161469.jpg@428w_240h_1e_1c",
            "https://p1.meituan.net/bbia/66fd7b3ea9b59d4687b42e07a514013593187.jpg@428w_240h_1e_1c",
            "https://p1.meituan.net/biztone/102024596_1664975588535.jpeg@428w_240h_1e_1c",
            "https://p1.meituan.net/biztone/1444238998_1641535147495.jpeg@428w_240h_1e_1c",
            "https://p1.meituan.net/biztone/728887985_1662627042049.jpeg@428w_240h_1e_1c",
            "https://p0.meituan.net/biztone/194785564_1665804102158.jpeg@428w_240h_1e_1c"
    };

    public String getImage() {
        int position = Math.min((int) (Math.random() * 10), 6);
        return this.images[position];
    }
}
