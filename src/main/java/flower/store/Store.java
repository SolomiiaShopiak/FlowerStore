package flower.store;

import java.util.List;
import java.util.stream.Collectors;

public class Store {
    private List<FlowerBucket> flowerBuckets;

    public Store(List<FlowerBucket> flowerBuckets) {
        this.flowerBuckets = flowerBuckets;
    }

    public List<FlowerPack> search(FlowerType type, FlowerColor color, Double price) {
        return flowerBuckets.stream()
            .flatMap(bucket -> bucket.getFlowerPacks().stream())
            .filter(pack -> (type == null || pack.getFlower().getFlowerType() == type))
            .filter(pack -> (color == null || pack.getFlower().getColor().equals(color.toString())))
            .collect(Collectors.toList());
    }
}
