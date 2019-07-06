using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.EventSystems;

public class ButtonEffect : MonoBehaviour, IPointerExitHandler, IPointerEnterHandler, IPointerDownHandler, IPointerUpHandler
{
    public void OnPointerEnter(PointerEventData eventData)
    {
        this.gameObject.transform.localScale = new Vector2(1.1f, 1.1f);
    }

    public void OnPointerExit(PointerEventData eventData)
    {
        this.gameObject.transform.localScale = new Vector2(1, 1);
    }

    public void OnPointerDown(PointerEventData eventData)
    {
        this.gameObject.transform.localScale = new Vector2(0.9f, 0.9f);
    }

    public void OnPointerUp(PointerEventData eventData)
    {
        if (Application.platform == RuntimePlatform.Android)
            this.gameObject.transform.localScale = new Vector2(1, 1);
        else this.gameObject.transform.localScale = new Vector2(1.1f, 1.1f);
    }
}
