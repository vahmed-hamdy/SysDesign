export default function CreateApplication() {
  return (
      <form onSubmit={() => alert('Submitting!')}>
          <input />
          <button>Send</button>
        </form>
      );
}