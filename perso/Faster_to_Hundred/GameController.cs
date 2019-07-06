using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class GameController : MonoBehaviour
{
    public GameObject winnerScreen;
    public Text recordText;
    private static int turno;
    private static bool music;
    public GameObject recordZone;
    public GameObject exitZone;
    public AudioSource audio;

    private void Start()
    {
        winnerScreen.SetActive(false);
        recordText.text = "Recorde: " + PlayerPrefs.GetInt("Record").ToString();
        recordZone.transform.position = new Vector2(Screen.width, Screen.height);
        exitZone.transform.position = new Vector2(0, Screen.height);
        if (music)
            audio.Play();
        else audio.Stop();
    }
    void Update()
    {
        if (Input.GetKeyDown(KeyCode.Escape))
            restart();
    }

    public void triggerWinnerScreen()
    {
        winnerScreen.SetActive(true);
        TotalScript.setLocked(false);
    }

    public void checkRecord()
    {
        int t = GameController.getTurnoValue();
        if (t < PlayerPrefs.GetInt("Record") || !PlayerPrefs.HasKey("Record"))
        {
            PlayerPrefs.SetInt("Record", t);
            recordText.text = "Recorde: " + t.ToString();
        }
    }
    public static void setTurnoValue(int v)
    {
        turno = v;
    }

    public static int getTurnoValue()
    {
        return turno;
    }

    public static void changeMusicState()
    {
        if (music)
            music = false;
        else music = true;
    }

    public static bool getMusicState()
    {
        return music;
    }

    public void clearRecord()
    {
        PlayerPrefs.DeleteAll();
        recordText.text = "Recorde: 0";
    }
    public void restart()
    {
        SceneManager.LoadScene(SceneManager.GetActiveScene().buildIndex);
    }

    public void exit()
    {
        MenuController.Restarting = true;
        SceneManager.LoadScene(0);
    }

}
