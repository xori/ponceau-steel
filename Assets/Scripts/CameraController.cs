using UnityEngine;
using System.Collections;

public class CameraController : MonoBehaviour {
	public float cameraGlue = 1;

	private Vector3 target;

	private Transform player;

	// Use this for initialization
	void Start () {
		player = GameObject.FindWithTag("Player").transform;
	}
	
	// Update is called once per frame
	void Update () {
		target = new Vector3(player.position.x, transform.position.y, player.position.z);
		transform.position = Vector3.Lerp(transform.position, target, cameraGlue);
	}
}
