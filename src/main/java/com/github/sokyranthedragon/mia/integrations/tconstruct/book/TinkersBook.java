package com.github.sokyranthedragon.mia.integrations.tconstruct.book;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.mantle.client.book.repository.FileRepository;
import slimeknights.tconstruct.library.book.TinkerBook;

import java.util.List;

// This class was taken from EnderIO
// Source 2: https://github.com/SleepyTrousers/EnderIO/blob/master/enderio-integration-tic/src/main/java/crazypants/enderio/integration/tic/book/TicBook.java
// It was slightly modified (to match this mod), but it's mostly the same class.

public class TinkersBook
{
    @SideOnly(Side.CLIENT)
    public static void registerBookPages(List<String> modifiersToAdd)
    {
        TinkerBook.INSTANCE.addRepository(new FileRepository("mia:tinkers_book"));
        TinkerBook.INSTANCE.addTransformer(new CustomBookTransformer(modifiersToAdd));
    }
}
