import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {WebcamImage} from "ngx-webcam";
import {lastValueFrom} from "rxjs";
import {Post} from "./models";

@Injectable()
export class CameraService {
	image!: WebcamImage

	constructor(private http: HttpClient) { }

	post(post: Post): Promise<any> {
		const formData = new FormData();
		// default is image/jpeg
    	//const f = new File([post.photo.imageData.data], 'webcam', { type: 'image/jpeg'})
		//formData.set("image", f)
		formData.set("image", this.convert(post.photo))
		formData.set("poster", post.poster);
		formData.set("comments", post.comments);

		return lastValueFrom(
			this.http.post('/post/s3', formData)
		)
	}

	convert(webcamImage: WebcamImage) {
		const arr = webcamImage.imageAsDataUrl.split(",");
		//@ts-ignore
		const mime = arr[0].match(/:(.*?);/)[1];
		console.info('>>> mime: ', mime)
		const bstr = atob(arr[1]);
		let n = bstr.length;
		const u8arr = new Uint8Array(n);
		while (n--) {
		  u8arr[n] = bstr.charCodeAt(n);
		}
		return new File([u8arr], 'webcam', { type: mime })
	}

}
