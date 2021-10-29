package com.example.demo.business;

public class BusinessParking {
        boolean garage;
        boolean street;
        boolean validated;
        boolean lot;
        boolean valet;

        public void setGarage(boolean garage) {
            this.garage = garage;
        }

        public void setStreet(boolean street) {
            this.street = street;
        }

        public void setValidated(boolean validated) {
            this.validated = validated;
        }

        public void setLot(boolean lot) {
            this.lot = lot;
        }

        public void setValet(boolean valet) {
            this.valet = valet;
        }

        public boolean isGarage() {
            return garage;
        }

        public boolean isStreet() {
            return street;
        }

        public boolean isValidated() {
            return validated;
        }

        public boolean isLot() {
            return lot;
        }

        public boolean isValet() {
            return valet;
        }
}
