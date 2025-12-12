package org.s3team.decoration.service;

import org.s3team.common.valueobject.Id;
import org.s3team.common.valueobject.Price;
import org.s3team.decoration.dao.DecorationDaoImpl;
import org.s3team.decoration.model.Decoration;
import org.s3team.decoration.model.Material;

import java.util.List;

public class DecorationService {

    private final DecorationDaoImpl decorationDaoImpl;

    public DecorationService() {
        this.decorationDaoImpl = new DecorationDaoImpl();
    }

    public void createDecoration(Decoration newDecoration) {

        int targetRoomId = newDecoration.getRoomId();
        List<Decoration> existingDecorations = decorationDaoImpl.findByRoomId(targetRoomId);

        if (existingDecorations.isEmpty()) {
            decorationDaoImpl.save(newDecoration);
            System.out.println("SUCCESS: First item added. Room " + targetRoomId + " is now a " + newDecoration.getMaterial() + " room.");

        } else {

            Material existingMaterial = existingDecorations.get(0).getMaterial();
            Material newMaterial = newDecoration.getMaterial();

            if (existingMaterial == newMaterial) {
                decorationDaoImpl.save(newDecoration);
                System.out.println("SUCCESS: Material matches (" + existingMaterial + "). Saved.");
            } else {
                String errorMessage = "RULE VIOLATION: Room " + targetRoomId +
                        " is a " + existingMaterial + " room. " +
                        "Cannot add " + newMaterial + " item.";

                throw new IllegalArgumentException(errorMessage);
            }
        }
    }

    public List<Decoration> getAllDecorations() {
        return decorationDaoImpl.findAll();
    }
    public Decoration findDecoracionById(Id<Decoration> id) {
        return null;
    }
    public Boolean updateDecoracion(Decoration decorationObject) {
        return null;
    }

    public boolean deleteDecoracion(Id<Decoration> id) {
        return true;
    }
    public int countDecoracion() {
        return 0;
    }
    public Price calculateDecoracionTotalPrice() {
        return null;
    }


}
