/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.data.field;

/**
 *
 * @author dimitrioskolovos
 *
 */
public abstract class AreaDto {

    private final double[] lat;
    private final double[] lng;

    public double[] getLat() {
        return lat;
    }

    public double[] getLng() {
        return lng;
    }

    private AreaDto(double[] lat, double[] lng) {
        this.lat = lat;
        this.lng = lng;
    }

    abstract float getSurfaceSize();

    public static class RectangularArea extends AreaDto {

        public RectangularArea(double[] lat, double[] lng) {
            super(lat, lng);
        }

        @Override
        float getSurfaceSize() {
            double[] lat = getLat();
            double[] lon = getLng();
            int n = lat.length;

            if (n != lon.length) {
                throw new IllegalArgumentException("Lat/Lng points must be passed in pairs.");
            }
            if (n != 4) {
                throw new IllegalArgumentException("At least 4 points required to compute a rectangular surface area.");
            }

            // Mean Earth radius in meters
            final double R = 6_371_000;

            // Use the “spherical excess” formula:
            // AreaDto = | Σ [ (λᵢ₊₁ − λᵢ) * (2 + sin φᵢ + sin φᵢ₊₁) ] | * R² / 2
            double total = 0.0;
            for (int i = 0; i < n; i++) {
                int j = (i + 1) % n;

                // convert to radians
                double phi1 = Math.toRadians(lat[i]);
                double lamda1 = Math.toRadians(lon[i]);
                double phi2 = Math.toRadians(lat[j]);
                double lamda2 = Math.toRadians(lon[j]);

                double deltaLamda = lamda2 - lamda1;
                total += deltaLamda * (2 + Math.sin(phi1) + Math.sin(phi2));
            }

            double area = Math.abs(total) * R * R / 2.0;
            return (float) area;
        }

    }

    public static class MixedShapeArea extends AreaDto {

        public MixedShapeArea(double[] lat, double[] lng) {
            super(lat, lng);
        }

        @Override
        float getSurfaceSize() {
            double[] lat = getLat();
            double[] lon = getLng();
            int n = lat.length;

            if (n != lon.length) {
                throw new IllegalArgumentException("Lat/Lng points must be passed in pairs.");
            }
            if (n < 3) {
                throw new IllegalArgumentException("At least 3 points required to compute a surface area.");
            }

            // Mean Earth radius in meters
            final double R = 6_371_000;

            // Use the “spherical excess” formula:
            // AreaDto = | Σ [ (λᵢ₊₁ − λᵢ) * (2 + sin φᵢ + sin φᵢ₊₁) ] | * R² / 2
            double total = 0.0;
            for (int i = 0; i < n; i++) {
                int j = (i + 1) % n;

                // convert to radians
                double phi1 = Math.toRadians(lat[i]);
                double lamda1 = Math.toRadians(lon[i]);
                double phi2 = Math.toRadians(lat[j]);
                double lamda2 = Math.toRadians(lon[j]);

                double deltaLamda = lamda2 - lamda1;
                total += deltaLamda * (2 + Math.sin(phi1) + Math.sin(phi2));
            }

            double area = Math.abs(total) * R * R / 2.0;
            return (float) area;

        }

    }

}
