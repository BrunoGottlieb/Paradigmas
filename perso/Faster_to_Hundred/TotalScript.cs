using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class TotalScript : MonoBehaviour
{
    private static int totalValue;
    private static Text thisText;
    private static bool locked;
    private void Start()
    {
        thisText = this.GetComponent<Text>();
        locked = false;
        totalValue = 0;
    }

    public static void setTotalValue(int value)
    {
        totalValue += value;
        thisText.text = totalValue.ToString();
        if (totalValue >= 100)
        {
            GameObject tmp = GameObject.Find("GameController");
            GameController other = (GameController)tmp.GetComponent(typeof(GameController));
            other.triggerWinnerScreen();
            GameObject resultText = GameObject.Find("ResultText");
            GameObject turnosText = GameObject.Find("TurnosText");
            resultText.GetComponent<Text>().text = turnosText.GetComponent<Text>().text;
            other.checkRecord();
        }
    }

    public static int getTotalValue()
    {
        return totalValue;
    }

    public static bool getLocked()
    {
        return locked;
    }

    public static void setLocked(bool b)
    {
        locked = b;
    }

}
