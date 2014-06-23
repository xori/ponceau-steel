using UnityEngine;
using System.Collections;

public class EnemyLogic : SquishyEntity
{
	// death animation
	private Material skin;
	private Color startColor;
	public float deathAnimationGrain = 20f;
	public float deathAnimationLength = 0.5f;

	public void Start() {
		skin = renderer.material;
		startColor = skin.color;
	}

	public override void Die (string reason)
	{
		StartCoroutine("Fade");
	}

	IEnumerator Fade() {

		for(var i = 0; i < deathAnimationGrain; i++) {
			skin.color = Color.Lerp(startColor, Color.clear, i/deathAnimationGrain);
			yield return new WaitForSeconds(deathAnimationLength/deathAnimationGrain);
		}
		Destroy(gameObject);
	}
}

