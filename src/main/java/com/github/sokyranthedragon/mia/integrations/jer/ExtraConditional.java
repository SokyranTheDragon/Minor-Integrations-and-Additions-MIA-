package com.github.sokyranthedragon.mia.integrations.jer;

import jeresources.api.conditionals.Conditional;
import jeresources.api.render.TextModifier;

public class ExtraConditional
{
    public static final Conditional affectedByResearch = new Conditional("mia.jer.affected_by_research", TextModifier.purple);
    public static final Conditional lessChanceIfHeld = new Conditional("mia.jer.less_chance_if_held", TextModifier.lightCyan);
    public static final Conditional ifLastOneKilled = new Conditional("mia.jer.if_last_one_killed", TextModifier.darkRed);
    public static final Conditional named = new Conditional("mia.jer.named", TextModifier.lilac);
    public static final Conditional isAdult = new Conditional("mia.jer.adult", TextModifier.orange);
    public static final Conditional isNotAdult = new Conditional("mia.jer.not_adult", isAdult);
    public static final Conditional carryingItem = new Conditional("mia.jer.carrying", TextModifier.lightGreen);
    
    private ExtraConditional()
    {
    }
}
