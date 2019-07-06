using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class SliderScript : MonoBehaviour
{
    public Text sliderValueText;
    private static int sliderValue = 1;

    public void updateSliderValue(float value)
    {
        sliderValue = (int)value;
        this.sliderValueText.text = sliderValue.ToString();
    }

    public static int getSliderValue()
    {
        return sliderValue;
    }

}
