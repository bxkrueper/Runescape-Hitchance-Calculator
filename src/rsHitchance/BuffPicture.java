package rsHitchance;

import images.Picture;

//stores a picture which may or may not be dependent on combat style
public interface BuffPicture {
    Picture getPicture(CombatStyle combatStyle);
}
