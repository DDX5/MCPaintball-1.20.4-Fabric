package org.multicoder.mcpaintball.utility;

public enum PaintballClass {
    STANDARD,
    SNIPER,
    HEAVY,
    MEDIC,
    ENGINEER;

    public String getTranslationKey()
    {
        switch (this)
        {
            case HEAVY -> {return "text.mcpaintball.class.heavy";}
            case STANDARD -> {return "text.mcpaintball.class.standard";}
            case SNIPER -> {return "text.mcpaintball.class.sniper";}
            case MEDIC -> {return "text.mcpaintball.class.medical";}
            case ENGINEER -> {return "text.mcpaintball.class.engineer";}
        }
        return "null";
    }
}
