using UnityEngine;
using System.Collections;

[RequireComponent (typeof(CharacterController))]
public class PlayerController : MonoBehaviour {

	public float moveSpeed = 5f;
	public float gravity = -3f;
	public float rotateSpeed = 450;
	public Weapon gun;

	private CharacterController controller;

	void Start () {
		controller = GetComponent<CharacterController>();
	}

	void Update () {
		Vector3 input = new Vector3(Input.GetAxisRaw("Horizontal"), 0, Input.GetAxisRaw("Vertical"));

		var objectPos = Camera.main.WorldToScreenPoint(transform.position);
		var dir = Input.mousePosition - objectPos; 
		transform.rotation = Quaternion.Slerp(
			transform.rotation,
			Quaternion.Euler(new Vector3(0,Mathf.Atan2 (dir.x,dir.y) * Mathf.Rad2Deg,0)),
			rotateSpeed * Time.deltaTime);

		Vector3 motion = input;
		// diagonal dampining
		motion *= (input.x != 0 && input.z != 0) ? 0.7f : 1f; 
		motion *= moveSpeed;
		motion += Vector3.up * (gravity);

		controller.Move(motion * Time.deltaTime);

		if(Input.GetButton("Shoot")) {
			gun.Shoot();
		}
	}
}
