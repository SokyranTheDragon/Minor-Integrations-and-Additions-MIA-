package com.github.exploder1531.mia.integrations.jer;

import jeresources.api.conditionals.Conditional;
import jeresources.api.render.TextModifier;

public class ExtraConditional
{
    public static final Conditional affectedByResearch;
    public static final Conditional lessChanceIfHeld;
    public static final Conditional ifLastOneKilled;
    
    private ExtraConditional()
    {
    }
    
    static
    {
        affectedByResearch = new Conditional("mia.jer.affected_by_research", TextModifier.purple);
        lessChanceIfHeld = new Conditional("mia.jer.less_chance_if_held", TextModifier.lightCyan);
        ifLastOneKilled = new Conditional("mia.jer.if_last_one_killed", TextModifier.darkRed);
    }
}
