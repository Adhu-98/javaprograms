import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;

import java.io.File;
import java.util.List;

public class ProductRecommender {

    public static void main(String[] args) {
        try {

            // Load dataset
            DataModel model = new FileDataModel(new File("data.csv"));

            // Compute similarity between users
            UserSimilarity similarity =
                    new PearsonCorrelationSimilarity(model);

            // Define neighborhood (nearest 2 users)
            UserNeighborhood neighborhood =
                    new NearestNUserNeighborhood(2, similarity, model);

            // Create recommender
            UserBasedRecommender recommender =
                    new GenericUserBasedRecommender(
                            model, neighborhood, similarity);

            // Generate recommendations for user 1
            List<RecommendedItem> recommendations =
                    recommender.recommend(1, 3);

            System.out.println("Recommended products for User 1:");

            for (RecommendedItem item : recommendations) {
                System.out.println("Product ID: "
                        + item.getItemID()
                        + " | Predicted Rating: "
                        + item.getValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
