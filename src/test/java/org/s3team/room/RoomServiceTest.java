package org.s3team.room;

import org.s3team.room.DAO.RoomDAO;
import org.s3team.room.Service.RoomService;
import org.s3team.theme.dao.ThemeDao;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * ClassName: RoomServiceTest
 * Package: org.s3team.room
 * Description:
 * Author: Rong Jiang
 * Create:16/12/2025 - 20:47
 * Version:v1.0
 *
 */
public class RoomServiceTest {
    @Mock
    RoomDAO roomDAO;

    @Mock
    ThemeDao themeDao;

    @InjectMocks
    RoomService roomService;
}
