package com.github.sokyranthedragon.mia.integrations.xu2;

import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import com.rwtema.extrautils2.api.machine.MachineSlotItem;

import javax.annotation.Nullable;

public interface IExtraUtilsIntegration extends IModIntegration
{
    void addRecipes(@Nullable MachineSlotItem slimeGeneratorSecondary);
}
