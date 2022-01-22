package cn.artwebs.control.ArtAnimation;

/**
 * Created by artwebs on 14/11/6.
 */
public abstract class ArtAnimationInteger implements ArtAnimationAbs.ArtAnimationNumberDelegate<Integer> {
    private ArtAnimationAbs<Integer> animationNumber;
//    private ArtAnimationNumber.ArtAnimationNumberDelegate<Integer> de


    public ArtAnimationAbs<Integer> getAnimationNumber() {
        return animationNumber;
    }

    public ArtAnimationInteger(){
        animationNumber=new ArtAnimationAbs<Integer>(this);
    }



    @Override
    public boolean isFinish(Integer currentValue, Integer startValue, Integer tagValue) {
        return tagValue.intValue()>startValue.intValue()?(currentValue.intValue()<tagValue.intValue()):(currentValue.intValue()>tagValue.intValue());
    }

    @Override
    public Integer intervalValue(Integer currentValue, Integer startValue, Integer tagValue) {
        return currentValue+(tagValue-startValue)/10;
    }

}
