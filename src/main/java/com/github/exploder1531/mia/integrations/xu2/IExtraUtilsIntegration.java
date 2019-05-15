package com.github.exploder1531.mia.integrations.xu2;

import com.github.exploder1531.mia.integrations.base.IModIntegration;
import com.rwtema.extrautils2.api.machine.MachineSlotItem;

import javax.annotation.Nullable;

public interface IExtraUtilsIntegration extends IModIntegration
{
    void addRecipes(@Nullable MachineSlotItem slimeGeneratorSecondary);
}
