using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.EventSystems;

public class SlotNumerico : MonoBehaviour
{     
    public void slotMeth()
    {
        if (!TotalScript.getLocked())
        {
            Text numberText = GetComponentInChildren<Text>();
            int value;
            int.TryParse(numberText.text, out value);
            TotalScript.setTotalValue(value);
            this.gameObject.GetComponent<Image>().color = Color.green;
            TotalScript.setLocked(true);
            numberText.text = "0";
        }
    }
}
