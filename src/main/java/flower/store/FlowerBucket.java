package flower.store;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class FlowerBucket {
    private List<FlowerPack> flowerPacks;

    public FlowerBucket() {
        flowerPacks = new ArrayList<>();
    }

    public void add(FlowerPack flowerPack) {
        flowerPacks.add(flowerPack);
    }

    public double getPrice() {
        return flowerPacks.stream()
                          .mapToDouble(flowerPack -> flowerPack.getPrice())
                          .sum();
    }
}
