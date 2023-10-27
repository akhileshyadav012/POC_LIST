package com.pattern.builder;
public class Product {
    private int productId;
    private String productName;
    private String productLocation;
    private Double productCost;

    public Product(ProductBuilder builder) {
        this.productId = builder.builderId;
        this.productName = builder.builderName;
        this.productLocation = builder.builderLocation;
        this.productCost = builder.builderCost;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductLocation() {
        return productLocation;
    }

    public Double getProductCost() {
        return productCost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productLocation='" + productLocation + '\'' +
                ", productCost=" + productCost +
                '}';
    }

    public static class ProductBuilder {
        private int builderId;
        private String builderName;
        private String builderLocation;
        private Double builderCost;

        public ProductBuilder setBuilderId(int builderId) {
            this.builderId = builderId;
            return this;
        }

        public ProductBuilder setBuilderName(String builderName) {
            this.builderName = builderName;
            return this;
        }

        public ProductBuilder setBuilderLocation(String builderLocation) {
            this.builderLocation = builderLocation;
            return this;
        }

        public ProductBuilder setBuilderCost(Double builderCost) {
            this.builderCost = builderCost;
            return this;
        }

        public Product build(){
            Product product = new Product(this);
            return product;
        }
    }
}
