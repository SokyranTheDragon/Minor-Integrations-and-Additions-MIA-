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
    public static final Conditional femaleOnly = new Conditional("mia.jer.female_only", TextModifier.lightCyan);
    public static final Conditional maleOnly = new Conditional("mia.jer.male_only", TextModifier.lightCyan);
    public static final Conditional requiresBottle = new Conditional("mia.jer.requires_bottle", TextModifier.white);
    public static final Conditional dependsOnAge = new Conditional("mia.jer.depends_on_age", TextModifier.orange);
    public static final Conditional dependsOnSize = new Conditional("mia.jer.depends_on_size", TextModifier.lightGreen);
    public static final Conditional affectedByAncient = new Conditional("mia.jer.affected_by_ancient", TextModifier.purple);
    
    private ExtraConditional()
    {
    }
}
