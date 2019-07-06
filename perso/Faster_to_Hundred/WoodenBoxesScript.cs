using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class WoodenBoxesScript : MonoBehaviour
{
    private static Text[] numberBox;

    void Start()
    {
        numberBox = GetComponentsInChildren<Text>();

        foreach (Text t in numberBox)
        {
            int rand = Random.Range(0,10);
            t.text = rand.ToString();
        }
    }

    public static Text getNumberBoxValue(int v)
    {
        return numberBox[v];
    }
    public void resetButtonsColors()
    {
        Button[] SlotNumerico = GetComponentsInChildren<Button>();
        foreach (Button b in SlotNumerico)
        {
            b.GetComponent<Image>().color = Color.white;
        }
    }
}
