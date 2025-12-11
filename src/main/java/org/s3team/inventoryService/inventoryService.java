package org.s3team.inventoryService;

import org.s3team.clue.model.ClueService;
import org.s3team.decoracion.DecorationService;
import org.s3team.room.Service.RoomService;

public class inventoryService {
    private ClueService clueService;
    private DecorationService decorationService;
    private RoomService roomService;

    public inventoryService(ClueService clueService, DecorationService decorationService, RoomService roomService) {
        this.clueService = clueService;
        this.decorationService = decorationService;
        this.roomService = roomService;
    }



}
