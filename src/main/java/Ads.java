import java.util.List;

public interface Ads {
    // insert a new ad and return the new ad's id
    long insert(Ad ad);
    // get a list of all the ads
    List<Ad> all();
//    List<Ad> allSortedByTitle();
//    List<Ad>getAllByUser(long userId);
//    List<Ad>getAllByCategory(String category);


//    Ad getOne(long id);
//    Ad retrieve(long id);

//    boolean update(Ad ad);
//    boolean refresh(Ad ad);
    boolean delete(long id);

}
