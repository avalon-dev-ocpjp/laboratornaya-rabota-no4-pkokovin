package ru.avalon.java.dev.ocpjp.labs.models;

public class CommodityImpl implements Commodity, Cloneable {
    private String code;
    private String vendorCode;
    private String name;
    private double price;
    private int resuide;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getVendorCode() {
        return vendorCode;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getResidue() {
        return resuide;
    }

    @Override
    protected CommodityImpl clone() {
        try {
            return (CommodityImpl) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException();
        }
    }

    public static class CommodityBuilderImpl implements CommodityBuilder {

        private final CommodityImpl dummy = new CommodityImpl();

        @Override
        public CommodityBuilder code(String code) {
            dummy.code = code;
            return this;
        }

        @Override
        public CommodityBuilder vendorCode(String vendorCode) {
            dummy.vendorCode = vendorCode;
            return this;
        }

        @Override
        public CommodityBuilder name(String name) {
            dummy.name = name;
            return this;
        }

        @Override
        public CommodityBuilder price(double price) {
            dummy.price = price;
            return this;
        }

        @Override
        public CommodityBuilder residue(int residue) {
            dummy.resuide = residue;
            return this;
        }

        @Override
        public Commodity build() {
            return dummy.clone();
        }
    }
}
