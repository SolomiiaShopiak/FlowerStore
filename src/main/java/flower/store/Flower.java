package flower.store;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Setter
@NoArgsConstructor
public class Flower {
    @Getter
    private double sepalLength;
    private FlowerColor color;
    @Getter
    private double price;
    @Getter
    private FlowerType flowerType;

    public Flower(Flower flower) {
        this.color = flower.color;
        this.flowerType = flower.flowerType;
        this.price = flower.price;
        this.sepalLength = flower.sepalLength;
    }

    public String getColor() {
        return color.toString();
    }
}
