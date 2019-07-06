using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class MenuController : MonoBehaviour
{
    public AudioSource audio;
    public GameObject tutorial;
    public GameObject[] tutorialLayers;
    public GameObject buttons;
    public GameObject firstTimeScreen;
    public GameObject introScreen;
    private bool menuOpcoesAberto;
    private Vector3 initialPos;
    public static bool Restarting;
    private int currentTutorialLayer = 0;

    private void Start()
    {
        initialPos = buttons.transform.position;
        if (!PlayerPrefs.HasKey("FirstTime"))
            firstTimeScreen.SetActive(true);
        else firstTimeScreen.SetActive(false);
        if (Restarting)
        {
            introScreen.SetActive(false);
            Restarting = false;
            tutorial.SetActive(false);
            if (GameController.getMusicState())
                audio.Play();
        }
    }

    public void jogar()
    {
        PlayerPrefs.SetInt("FirstTime", 1);
        StartCoroutine(wait(1));
    }

    public void sair()
    {
        Application.Quit();
    }

    IEnumerator wait(float tempo)
    {
        yield return new WaitForSeconds(tempo);
        SceneManager.LoadScene("Jogo");
    }

    IEnumerator deslocarUp()
    {
        yield return new WaitForSeconds(0.01f);
        if (buttons.transform.localPosition.y < 60)
        {
            buttons.transform.position += new Vector3(0, 10);
            StartCoroutine(deslocarUp());
        }
        menuOpcoesAberto = true;
    }

    IEnumerator deslocarDown()
    {
        yield return new WaitForSeconds(0.01f);
        if (buttons.transform.position.y > initialPos.y)
        {
            buttons.transform.position -= new Vector3(0, 10);
            StartCoroutine(deslocarDown());
        }
        menuOpcoesAberto = false;
    }

    public void opcoes()
    {
        if (!menuOpcoesAberto)
            StartCoroutine(deslocarUp());
        else StartCoroutine(deslocarDown());
    }

    public void checkMusic()
    {
        if (PlayerPrefs.HasKey("FirstTime"))
        {
            GameController.changeMusicState();
            audio.Play();
        }
    }

    public void music()
    {
        GameController.changeMusicState();
        if (GameController.getMusicState())
            audio.Play();
        else audio.Stop();
    }

    public void tutorialManager(int i)
    {

        foreach (GameObject g in tutorialLayers)
            g.SetActive(false);

        if (currentTutorialLayer == 5)
        {
            tutorial.SetActive(false);
            currentTutorialLayer = 0;
            audio.Play();
        }
        else if (i == 1) currentTutorialLayer++;
        else if (currentTutorialLayer >= 1) currentTutorialLayer--;

        tutorialLayers[currentTutorialLayer].SetActive(true);

    }

    public void setTutorialState(bool state)
    {
        tutorial.SetActive(state);
    }

}
