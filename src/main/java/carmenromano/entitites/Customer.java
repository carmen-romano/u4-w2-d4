package carmenromano.entitites;

import java.util.Random;

public class Customer {
    private Long id;
    private String name;
    private int tier;

    public Customer(String name, int tier) {
        this.name = name;
        Random random = new Random();
        this.id = random.nextLong();
        this.tier = tier;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tier=" + tier +
                '}';
    }
}
