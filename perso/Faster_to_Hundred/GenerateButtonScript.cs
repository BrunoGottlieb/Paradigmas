using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class GenerateButtonScript : MonoBehaviour
{
    private int turno = 0;
    public Text turnoText;

    public void generate()
    {
        int value = SliderScript.getSliderValue();
        TotalScript.setTotalValue(-value);
        for (int i=0; i < value; i++)
        {
            Text t = WoodenBoxesScript.getNumberBoxValue(i);
            t.text = (Random.Range(0,10)).ToString();
        }
        TotalScript.setLocked(false);
        turno++;
        turnoText.text = "Turnos decorridos: " + turno.ToString();
        GameController.setTurnoValue(turno);
    }
}
